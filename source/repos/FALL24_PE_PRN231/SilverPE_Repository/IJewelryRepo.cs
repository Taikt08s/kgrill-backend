using SilverPE_BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_Repository
{
    public interface IJewelryRepo
    {
        public bool AddJewelry(SilverJewelry silverJewelry);
        public bool UpdateJewelry(SilverJewelry silverJewelry);
        public bool DeleteJewelry(string id);

        public List<SilverJewelry> GetSilverJewelries();
        object GetSilverJewelry(string id);
    }
}
