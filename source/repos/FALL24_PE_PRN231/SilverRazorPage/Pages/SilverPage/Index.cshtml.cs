using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using SilverPE_BusinessObject;

namespace SilverRazorPage.Pages.SilverPage
{
    public class IndexModel : PageModel
    {

        public IndexModel()
        {
        }

        public IList<SilverJewelry> SilverJewelry { get;set; } = default!;

        public async Task OnGetAsync()
        {
        }
    }
}
