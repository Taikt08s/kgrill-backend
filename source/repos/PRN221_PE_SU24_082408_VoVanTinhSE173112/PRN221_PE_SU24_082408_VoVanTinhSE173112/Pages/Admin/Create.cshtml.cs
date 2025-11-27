using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Repositories.Models;

namespace PRN221_PE_SU24_082408_VoVanTinh.Pages.Admin
{
    public class CreateModel : PageModel
    {
        private readonly Repositories.Models.Euro2024DbContext _context;

        public CreateModel(Repositories.Models.Euro2024DbContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
        ViewData["GroupId"] = new SelectList(_context.GroupTeams, "GroupId", "GroupId");
            return Page();
        }

        [BindProperty]
        public Team Team { get; set; } = default!;
        

        // To protect from overposting attacks, see https://aka.ms/RazorPagesCRUD
        public async Task<IActionResult> OnPostAsync()
        {
          if (!ModelState.IsValid || _context.Teams == null || Team == null)
            {
                return Page();
            }

            _context.Teams.Add(Team);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}
