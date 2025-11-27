using Microsoft.EntityFrameworkCore;
using SilverPE_BusinessObject;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace SilverPE_DAO
{
    public class JewelryDAO
    {
        private readonly SilverJewelry2023DbContext _context;
        private static JewelryDAO instance;

        public static JewelryDAO Instance
        {
            get
            {
                if (instance == null)
                {
                    instance = new JewelryDAO();
                }
                return instance;
            }
        }

        private JewelryDAO()
        {
            _context = new SilverJewelry2023DbContext();
        }

        public bool AddJewelry(SilverJewelry silverJewelry)
        {
            bool result = false;
            SilverJewelry newJewelry = this.getSilverJewelry(silverJewelry.SilverJewelryId);
            if (newJewelry != null)
            {
                try
                {
                    _context.SilverJewelries.Add(silverJewelry);
                    _context.SaveChanges();
                    result = true;
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                }
            }

            return result;
        }
        public bool UpdateJewelry(SilverJewelry silverJewelry)
        {
            bool result = false;
            SilverJewelry newJewelry = this.getSilverJewelry(silverJewelry.SilverJewelryId);
            if (newJewelry != null)
            {
                _context.Entry(newJewelry).CurrentValues.SetValues(silverJewelry);
                _context.SaveChanges();
                result = true;
            }

            return result;
        }
        public bool DeleteJewelry(string id)
        {
            bool result = false;
            SilverJewelry newJewelry = this.getSilverJewelry(id);
            if (newJewelry != null)
            {
                _context.SilverJewelries.Remove(newJewelry);
                _context.SaveChanges();
                result = true;
            }

            return result;
        }

        public SilverJewelry getSilverJewelry(string id)
        {
            return _context.SilverJewelries.SingleOrDefault(x => x.SilverJewelryId.Equals(id));
        }

        public List<SilverJewelry> GetSilverJewelries()
        {
            return _context.SilverJewelries.ToList();
        }
    }
}
