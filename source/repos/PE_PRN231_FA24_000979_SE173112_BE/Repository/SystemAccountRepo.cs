using BusinessObject;
using DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class SystemAccountRepo : ISystemAccountRepo
    {
        public SystemAccount GetSystemAccount(string email, string password)
        {
            return SystemAccountDAO.Instance.GetSystemAccount(email, password);
        }
    }
}
