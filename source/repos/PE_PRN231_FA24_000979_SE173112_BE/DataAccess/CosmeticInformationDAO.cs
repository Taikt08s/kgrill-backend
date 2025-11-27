using BusinessObject;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class CosmeticInformationDAO
    {
        private Fall24CosmeticsDbContext _context;
        private static CosmeticInformationDAO _instance;

        public CosmeticInformationDAO()
        {
            _context = new Fall24CosmeticsDbContext();
        }

        public static CosmeticInformationDAO Instance
        {
            get
            {
                if (_instance == null)
                {
                    _instance = new CosmeticInformationDAO();
                }
                return _instance;
            }
        }
        public CosmeticInformation GetCosmetic(string id)
        {
            return _context.CosmeticInformations.Include(s => s.Category).SingleOrDefault(s => s.CosmeticId.Equals(id));
        }

        public List<CosmeticInformation> GetCosmetics()
        {
            return _context.CosmeticInformations.Include(s => s.Category).ToList();
        }

        public bool AddCosmetic(CosmeticInformation Cosmetic)
        {
            bool result = false;
            CosmeticInformation cosmetic1 = this.GetCosmetic(Cosmetic.CosmeticId);
            if (cosmetic1 == null)
            {
                try
                {
                    _context.CosmeticInformations.Add(Cosmetic);
                    _context.SaveChanges();
                    result = true;
                }
                catch (Exception ex)
                {

                }
            }

            return result;
        }
        public bool DeleteCosmetic(string cosmeticId)
        {
            bool result = false;
            CosmeticInformation cosmetic1 = this.GetCosmetic(cosmeticId);
            if (cosmetic1 != null)
            {
                try
                {
                    _context.CosmeticInformations.Remove(cosmetic1);
                    _context.SaveChanges();
                    result = true;
                }
                catch (Exception ex)
                {

                }
            }

            return result;
        }
        public bool UpdateCosmetic(CosmeticInformation cosmetic)
        {
            bool result = false;
            CosmeticInformation cosmetic1 = this.GetCosmetic(cosmetic.CosmeticId);
            if (cosmetic1 != null)
            {
                try
                {
                    /*                    _context.Entry<CosmeticInformation>(cosmetic1).State
                                = Microsoft.EntityFrameworkCore.EntityState.Modified;*/

                    _context.Entry(cosmetic1).CurrentValues.SetValues(cosmetic);
                    _context.SaveChanges();
                    result = true;
                }
                catch (Exception ex)
                {

                }
            }

            return result;
        }
    }
}
