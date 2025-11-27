using Repositories.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repositories
{
    public class AccountRepository
    {
        private readonly Euro2024DbContext _context;

        public AccountRepository(Euro2024DbContext context)
        {
            _context = context;
        }

        public void AddAccount()
        {
            // Add account
        }

        public void UpdateAccount()
        {
            // Update account
        }

        public void DeleteAccount()
        {
            // Delete account
        }

        public Account GetAccount(string email, string password)
        {
            try
            {
                return _context.Accounts.FirstOrDefault(a => a.Email == email && a.Password == password && a.Status.Equals("active"));
            }
            catch (Exception ex)
            {
                throw new Exception(ex.Message);
            }
        }
    }
}
