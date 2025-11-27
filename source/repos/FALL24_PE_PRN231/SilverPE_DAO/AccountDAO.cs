using SilverPE_BusinessObject;
using System.Numerics;

namespace SilverPE_DAO
{
    public class AccountDAO
    {
        private readonly SilverJewelry2023DbContext _context;

        private static AccountDAO instance;

        public static AccountDAO Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new AccountDAO();
                }
                return instance;
            }
        }

        private AccountDAO()
        {
            _context = new SilverJewelry2023DbContext();
        }

        public bool Login(string email, string password)
        {
            BranchAccount? account = _context.BranchAccounts.SingleOrDefault(x => x.EmailAddress.Equals(email) && x.AccountPassword.Equals(password));

            if (account == null)
            {
                return false;
            }

            return true;
        }

    }
}
