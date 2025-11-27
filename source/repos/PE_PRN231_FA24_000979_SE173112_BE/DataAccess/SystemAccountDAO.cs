using BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class SystemAccountDAO
    {
        private Fall24CosmeticsDbContext _context;
        private static SystemAccountDAO _instance;

        public SystemAccountDAO()
        {
            _context = new Fall24CosmeticsDbContext();
        }

        public static SystemAccountDAO Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new SystemAccountDAO();
                }
                return _instance;
            }
        }
        public SystemAccount GetSystemAccount(string email, string password)
        {
            return _context.SystemAccounts.SingleOrDefault(a => a.EmailAddress.Equals(email) && a.AccountPassword.Equals(password));
        }
    }
}
