using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using BusinessObject;
using DataAccess;
using System.Net.Http.Headers;
using System.Text.Json;

namespace PE_PRN231_FA24_000979_VoVanTinh_FE.Pages.CosmeticPage
{
    public class IndexModel : PageModel
    {
        private readonly HttpClient _httpClient;
        public IndexModel()
        {
            _httpClient = new HttpClient();
        }

        public IList<CosmeticInformation> CosmeticInformation { get; set; }

        public async Task<IActionResult> OnGetAsync()
        {
            var role = HttpContext.Session.GetString("Role");
            if ((role != null && role.Equals("1")) || (role != null && role.Equals("2")))
            {
                var token = HttpContext.Session.GetString("JwtToken");

                if (string.IsNullOrEmpty(token))
                {
                    //return Unauthorized();
                    return RedirectToPage("/Login/Login", new { errorMessage = "You do not have permission to do this function!" });
                }
                string apiUrl;

             
                    apiUrl = "http://localhost:7076/api/CosmeticInformation/all";


                var requestMessage = new HttpRequestMessage(HttpMethod.Get, apiUrl);
                requestMessage.Headers.Authorization = new AuthenticationHeaderValue("Bearer", token);
                var response = await _httpClient.SendAsync(requestMessage);
                if (response.StatusCode == System.Net.HttpStatusCode.OK)
                {
                    var options = new JsonSerializerOptions
                    {
                        PropertyNameCaseInsensitive = true,
                    };
                    var data = await response.Content.ReadAsStringAsync();
                    CosmeticInformation = JsonSerializer.Deserialize<List<CosmeticInformation>>(data, options);
                    return Page();
                }
                else
                {
                  
                    return RedirectToPage("/Login/Login", new { errorMessage = "You do not have permission to do this function!" });
                }
            }
            else
            {
                return RedirectToPage("/Login/Login");
            }
        }
    }
}
