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
    public class IndexModel : PageModel
    {
        private readonly Repositories.Models.Euro2024DbContext _context;

        public IndexModel(Repositories.Models.Euro2024DbContext context)
        {
            _context = context;
        }

        public IList<Team> Team { get;set; } = default!;

        public async Task OnGetAsync()
        {
            if (_context.Teams != null)
            {
                Team = await _context.Teams
                .Include(t => t.Group).ToListAsync();
            }
        }
    }
}
