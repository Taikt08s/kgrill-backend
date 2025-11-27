using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using Repositories.Models;

namespace PRN221_PE_SU24_082408_VoVanTinh.Pages.Admin
{
    public class DeleteModel : PageModel
    {
        private readonly Repositories.Models.Euro2024DbContext _context;

        public DeleteModel(Repositories.Models.Euro2024DbContext context)
        {
            _context = context;
        }

        [BindProperty]
      public Team Team { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Teams == null)
            {
                return NotFound();
            }

            var team = await _context.Teams.FirstOrDefaultAsync(m => m.Id == id);

            if (team == null)
            {
                return NotFound();
            }
            else 
            {
                Team = team;
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.Teams == null)
            {
                return NotFound();
            }
            var team = await _context.Teams.FindAsync(id);

            if (team != null)
            {
                Team = team;
                _context.Teams.Remove(Team);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
