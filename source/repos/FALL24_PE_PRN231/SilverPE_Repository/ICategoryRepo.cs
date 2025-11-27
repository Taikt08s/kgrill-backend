using SilverPE_BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_Repository
{
    public interface ICategoryRepo
    {
        public bool AddCategory(CategoryRepo category);
        public bool UpdateCategory(CategoryRepo category);
        public bool DeleteCategory(string id);
   
    }
}
