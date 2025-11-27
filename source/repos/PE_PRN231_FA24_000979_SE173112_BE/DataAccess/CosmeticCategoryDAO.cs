using BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class CosmeticCategoryDAO
    {
        private Fall24CosmeticsDbContext _context;
        private static CosmeticCategoryDAO _instance;

        public CosmeticCategoryDAO()
        {
            _context = new Fall24CosmeticsDbContext();
        }

        public static CosmeticCategoryDAO Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new CosmeticCategoryDAO();
                }
                return _instance;
            }
        }
        public List<CosmeticCategory> GetCategories()
        {
            return _context.CosmeticCategories.ToList();
        }
        public CosmeticCategory GetCategory(string id)
        {
            return _context.CosmeticCategories.SingleOrDefault(c => c.CategoryId.Equals(id));
        }
    }
}
