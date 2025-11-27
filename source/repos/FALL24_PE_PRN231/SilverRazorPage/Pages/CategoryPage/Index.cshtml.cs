using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using SilverPE_BusinessObject;

namespace SilverRazorPage.Pages.CategoryPage
{
    public class IndexModel : PageModel
    {
        private readonly HttpClient _httpClient;

        public IndexModel()
        {
            _httpClient = new HttpClient();
        }

        public IList<Category> Category { get; set; } = default!;

        public async Task OnGetAsync()
        {
            HttpResponseMessage respone = await _httpClient.GetAsync("http://localhost:5033/odata/SilverJewelry");
            var options = new JsonSerializerOptions
            {
                PropertyNameCaseInsensitive = true,
            };
            var data = await respone.Content.ReadAsStringAsync();
            Category = JsonSerializer.Deserialize<List<Category>>(data, options);
        }

    }
}
