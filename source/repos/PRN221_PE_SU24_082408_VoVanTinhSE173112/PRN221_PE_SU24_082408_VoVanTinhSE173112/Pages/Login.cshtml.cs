using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Repositories.Models;
using Services;

namespace PRN221_PE_SU24_082408_VoVanTinhSE173112.Pages
{
    public class LoginModel : PageModel
    {
        private readonly AccountService accountService;

        public LoginModel(AccountService accountService)
        {
            this.accountService = accountService;
        }

        [BindProperty]
        public Account account { get; set; }

        public void OnGet()
        {
            if (HttpContext.Session.GetString("UserRole") != null)
            {
                var role = int.Parse(HttpContext.Session.GetString("UserRole"));
                if (role == 2 || role == 3)
                {
                    Response.Redirect("Admin/Index");
                }
            }
        }

        public async Task<IActionResult> OnPostAsync()
        {
            var email = account.Email;
            var password = account.Password;
            account = accountService.Login(email, password);
            if (account != null)
            {
                if (account.RoleId == 1 || account.RoleId == 2)
                {
                    HttpContext.Session.SetString("UserRole", account.RoleId.ToString());
                    return RedirectToPage("/Admin/Index");
                }
                else
                {
                    ModelState.AddModelError("Error", "You do not have permission to do this function");
                    return Page();
                }

            } else
            {
                ModelState.AddModelError("Error", "Email or password is invalid !");
                return Page();
            }
        }
    }
}
