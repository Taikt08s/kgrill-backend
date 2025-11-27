using SilverPE_BusinessObject;
using SilverPE_DAO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_Repository
{
    public class JewelryRepo : IJewelryRepo
    {
        public bool AddJewelry(SilverJewelry silverJewelry)
        {
            throw new NotImplementedException();
        }

        public bool DeleteJewelry(string id)
        {
            throw new NotImplementedException();
        }

        public List<SilverJewelry> GetSilverJewelries()
        {
            return JewelryDAO.Instance.GetSilverJewelries();
        }

        public object GetSilverJewelry(string id)
        {
            return JewelryDAO.Instance.getSilverJewelry(id);
        }

        public bool UpdateJewelry(SilverJewelry silverJewelry)
        {
            throw new NotImplementedException();
        }
    }
}
