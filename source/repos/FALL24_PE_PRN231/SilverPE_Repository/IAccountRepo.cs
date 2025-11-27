using SilverPE_BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_Repository
{
    public interface IAccountRepo
    {
        public bool AddAccount(BranchAccount account);
        public bool UpdateAccount(BranchAccount account);
        public bool DeleteAccout(string id);

        public bool SignIn(string email, string password);
    }
}
