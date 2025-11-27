using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace Euro2024DB_VoVanTinh.Pages
{
    public class LogoutModel : PageModel
    {
        public void OnGet()
        {
            HttpContext.Session.Remove("UserRole");
            RedirectToPage("/Login");
        }
    }
}
