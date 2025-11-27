using BusinessObject;
using DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class CosmeticCategoryRepo : ICosmeticCategoryRepo
    {
        public List<CosmeticCategory> GetCategories()
        {
            return CosmeticCategoryDAO.Instance.GetCategories();
        }

        public CosmeticCategory GetCategory(string id)
        {
           return CosmeticCategoryDAO.Instance.GetCategory(id);
        }
    }
}
