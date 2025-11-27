using SilverPE_BusinessObject;
using SilverPE_DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_Repository
{
    public class AccountRepo : IAccountRepo
    {
        public bool AddAccount(BranchAccount account)
        {
            throw new NotImplementedException();
        }

        public bool DeleteAccout(string id)
        {
            throw new NotImplementedException();
        }

        public bool SignIn(string email, string password)
        {
            return AccountDAO.Instance.Login(email, password);
        }

        public bool UpdateAccount(BranchAccount account)
        {
            throw new NotImplementedException();
        }
    }
}
