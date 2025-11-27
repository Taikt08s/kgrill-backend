using BusinessObject;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.OData.Formatter;
using Microsoft.AspNetCore.OData.Query;
using Microsoft.AspNetCore.OData.Routing.Controllers;
using Repository;

namespace PE_PRN231_FA24_000979_SE173112_BE.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CosmeticInformationController : ODataController
    {
        private readonly ICosmeticInformationRepo cosmeticInformationRepo;
        private readonly ISystemAccountRepo accountRepository;
        private readonly ICosmeticCategoryRepo categoryRepository;

        public CosmeticInformationController(ICosmeticInformationRepo cosmeticInformationRepo, ISystemAccountRepo accountRepository, ICosmeticCategoryRepo categoryRepository)
        {
            this.cosmeticInformationRepo = cosmeticInformationRepo;
            this.accountRepository = accountRepository;
            this.categoryRepository = categoryRepository;
        }

        [Authorize(Roles = "1,3,4")]
        [EnableQuery]
        [HttpGet]
        public IActionResult Get()
        {
            return Ok(categoryRepository.GetCategories());
        }

        [Authorize(Roles = "1")]
        [EnableQuery]
        [HttpGet("id")]
        public IActionResult GetById([FromODataUri] string id)
        {
            var entity = cosmeticInformationRepo.GetCosmetic(id);
            if (entity == null)
            {
                return NotFound();
            }
            return Ok(entity);
        }

        [Authorize(Roles = "1,3,4")]
        [EnableQuery]
        [HttpGet("all")]
        public IActionResult GetByIdGetCosmetics()
        {
            return Ok(cosmeticInformationRepo.GetCosmetics());
        }

        [Authorize(Roles = "1")]
        [HttpPost("create")]
        public IActionResult CreateCosmeticInformation([FromBody] CosmeticInformation cosmetic)
        {
            cosmeticInformationRepo.AddCosmetic(cosmetic);
            //string locationUri = Url.Link("GetById", new { id = cosmetic.CosmeticId });
            return Ok("Save Successfully");
        }

        [Authorize(Roles = "1")]
        [HttpPost("update")]
        public IActionResult UpdateCosmeticInformation([FromBody] CosmeticInformation cosmetic)
        {
            cosmeticInformationRepo.UpdateCosmetic(cosmetic);
            //string locationUri = Url.Link("GetById", new { id = cosmetic.CosmeticId });
            return Ok("Update Successfully");
        }

        [Authorize(Roles = "1")]
        [HttpPost("delete")]
        public IActionResult UpdateCosmeticInformation([FromQuery] string id)
        {
            cosmeticInformationRepo.DeleteCosmetic(id);
            //string locationUri = Url.Link("GetById", new { id = cosmetic.CosmeticId });
            return Ok("Delete Successfully");
        }

        [Authorize(Roles = "1,3,4")]
        [EnableQuery]
        [HttpGet("search")]
        public IActionResult SearchCosmeticInformation()
        {
            var cosmetics = cosmeticInformationRepo.GetCosmetics();
            return Ok(cosmetics);
        }

    }
}
