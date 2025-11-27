using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.OData.Formatter;
using Microsoft.AspNetCore.OData.Query;
using Microsoft.AspNetCore.OData.Routing.Controllers;
using Microsoft.IdentityModel.Tokens;
using SilverPE_BusinessObject;
using SilverPE_Repository;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace SilverPE_API.Controllers
{
    [Route("odata/[controller]")]
    [ApiController]
    public class SilverJewelryController : ODataController
    {
        private readonly IJewelryRepo _jewelryRepo;
        private readonly IAccountRepo _accountRepo;
        private readonly ICategoryRepo _categoryRepo;

        public SilverJewelryController(IJewelryRepo jewelryRepo, IAccountRepo accountRepo, ICategoryRepo categoryRepo)
        {
            _jewelryRepo = jewelryRepo;
            _accountRepo = accountRepo;
            _categoryRepo = categoryRepo;
        }

        [Authorize(Roles = "1")]
        [EnableQuery]
        [HttpGet]
        public IActionResult Get()
        {
            var jewelry = _jewelryRepo.GetSilverJewelries();
            return Ok(jewelry);
        }

        [EnableQuery]
        [HttpGet("id")]
        public IActionResult GetById([FromODataUri] string id)
        {
            var jewelry = _jewelryRepo.GetSilverJewelry(id);
            return Ok(jewelry);
        }

        [HttpPost("sign-in")]
        public IActionResult SignIn(string email, string password)
        {
            var result = _accountRepo.SignIn(email, password);
            if (!result)
            {
                return BadRequest("Invalid username or password");
            }

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("SE173112VoVanTinhTinhvvse173112@fpt.edu.vn"));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
              issuer: "github.Tinhhhh",
              audience: "Tinhhhh",
              claims: new[] {
                      new Claim("sub", email),
              },
              expires: DateTime.Now.AddMinutes(30),
              signingCredentials: creds);

            string savedToken =  new JwtSecurityTokenHandler().WriteToken(token);

            Dictionary<string, string> keyValuePairs = new Dictionary<string, string>();

            keyValuePairs.Add("token", savedToken);

            return Ok(keyValuePairs);
        }


        [HttpGet("/validate")]
        public ClaimsPrincipal ValidateToken(string jwt)
        {

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes("SE173112VoVanTinhTinhvvse173112@fpt.edu.vn"));

            var tokenValidationParameters = new TokenValidationParameters
            {
                ValidateIssuerSigningKey = true,
                IssuerSigningKey = key,
                ValidateIssuer = false,
                ValidateAudience = false
            };

            SecurityToken securityToken = null;

            var claimsPrincipal = new JwtSecurityTokenHandler()
          .ValidateToken(jwt, tokenValidationParameters, out securityToken);

            return claimsPrincipal;
        }


    }
}
