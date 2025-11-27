using SilverPE_BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_DAO
{
    public class CategoryDAO
    {
        private readonly SilverJewelry2023DbContext _context;
        private static CategoryDAO instance;

        public static CategoryDAO Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new CategoryDAO();
                }
                return instance;
            }
        }

        private CategoryDAO()
        {
            _context = new SilverJewelry2023DbContext();
        }

    }
}
