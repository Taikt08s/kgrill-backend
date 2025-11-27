using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using SilverPE_BusinessObject;

namespace SilverRazorPage.Pages.SilverPage
{
    public class CreateModel : PageModel
    {
        private readonly SilverPE_BusinessObject.SilverJewelry2023DbContext _context;

        public CreateModel(SilverPE_BusinessObject.SilverJewelry2023DbContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
        ViewData["CategoryId"] = new SelectList(_context.Categories, "CategoryId", "CategoryId");
            return Page();
        }

        [BindProperty]
        public SilverJewelry SilverJewelry { get; set; } = default!;

        // For more information, see https://aka.ms/RazorPagesCRUD.
        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            _context.SilverJewelries.Add(SilverJewelry);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}
