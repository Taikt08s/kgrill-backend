using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using BusinessObject;
using DataAccess;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using System.Text.Json;

namespace PE_PRN231_FA24_000979_VoVanTinh_FE.Pages.Login
{
    public class LoginModel : PageModel
    {
        private readonly HttpClient _httpClient;

        public LoginModel()
        {
            _httpClient = new HttpClient();
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        [BindProperty]
        public SystemAccount SystemAccount { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            LoginRequest request = new LoginRequest();
            request.email = SystemAccount.EmailAddress;
            request.password = SystemAccount.AccountPassword;
            var jsonContent = new StringContent(
            JsonSerializer.Serialize(request),
            Encoding.UTF8,
            "application/json"
            );
            HttpResponseMessage response = await _httpClient.PostAsync($"http://localhost:7076/api/Jwt", jsonContent);

            if (response.IsSuccessStatusCode)
            {
                var responseData = await response.Content.ReadAsStringAsync();
                HttpContext.Session.SetString("JwtToken", responseData);
                var role = GetRoleFromToken(responseData);

                HttpContext.Session.SetString("Role", role);

                Console.WriteLine("Created Cosmetic: " + responseData);
                return RedirectToPage("/CosmeticPage/Index");

            }
            else
            {
                Console.WriteLine("Error: " + response.StatusCode);
                return RedirectToPage("/Login/Login", new { errorMessage = "You do not have permission to do this function!" });
            }
        }

        private string GetRoleFromToken(string jwtToken)
        {
            var handler = new JwtSecurityTokenHandler();
            var jsonToken = handler.ReadToken(jwtToken) as JwtSecurityToken;

            // Lấy claim có tên là "roles"
            var role = jsonToken?.Claims
                .FirstOrDefault(claim => claim.Type == "roles" || claim.Type == ClaimTypes.Role)?.Value;

            return role ?? string.Empty;
        }
    }
}
