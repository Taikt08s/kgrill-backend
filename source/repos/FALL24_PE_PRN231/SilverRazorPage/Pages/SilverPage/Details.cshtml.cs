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
    public class DetailsModel : PageModel
    {
        private readonly SilverPE_BusinessObject.SilverJewelry2023DbContext _context;

        public DetailsModel(SilverPE_BusinessObject.SilverJewelry2023DbContext context)
        {
            _context = context;
        }

        public SilverJewelry SilverJewelry { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(string id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var silverjewelry = await _context.SilverJewelries.FirstOrDefaultAsync(m => m.SilverJewelryId == id);
            if (silverjewelry == null)
            {
                return NotFound();
            }
            else
            {
                SilverJewelry = silverjewelry;
            }
            return Page();
        }
    }
}
