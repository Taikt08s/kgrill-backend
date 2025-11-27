using BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Repository
{
    public interface ICosmeticInformationRepo
    {
        public CosmeticInformation GetCosmetic(string id);
        public List<CosmeticInformation> GetCosmetics();
        public bool AddCosmetic(CosmeticInformation Cosmetic);
        public bool DeleteCosmetic(string cosmeticId);
        public bool UpdateCosmetic(CosmeticInformation cosmetic);
    }
}
