using BusinessObject;
using DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public class CosmeticInformationRepo : ICosmeticInformationRepo
    {
        public bool AddCosmetic(CosmeticInformation Cosmetic)
        {
            return CosmeticInformationDAO.Instance.AddCosmetic(Cosmetic);
        }

        public bool DeleteCosmetic(string cosmeticId)
        {
            return CosmeticInformationDAO.Instance.DeleteCosmetic(cosmeticId);
        }

        public CosmeticInformation GetCosmetic(string id)
        {
            return CosmeticInformationDAO.Instance.GetCosmetic(id);
        }

        public List<CosmeticInformation> GetCosmetics()
        {
            return CosmeticInformationDAO.Instance.GetCosmetics();
        }

        public bool UpdateCosmetic(CosmeticInformation cosmetic)
        {
            return CosmeticInformationDAO.Instance.UpdateCosmetic(cosmetic);
        }
    }
}
