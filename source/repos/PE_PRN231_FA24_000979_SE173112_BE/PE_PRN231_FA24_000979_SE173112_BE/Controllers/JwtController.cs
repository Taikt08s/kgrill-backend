using BusinessObject;
using DataAccess;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using Repository;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace PE_PRN231_FA24_000979_SE173112_BE.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class JwtController : ControllerBase
    {
        private readonly IConfiguration _configuration;
        private readonly ISystemAccountRepo _accountRepository;

        public JwtController(IConfiguration configuration, ISystemAccountRepo accountRepository)
        {
            _configuration = configuration;
            _accountRepository = accountRepository;
        }


        [HttpPost]
        public IActionResult generateToken([FromBody] LoginRequest loginRequest)
        {
            SystemAccount systemAccount = _accountRepository.GetSystemAccount(loginRequest.email, loginRequest.password);

            if (systemAccount == null)
            {
                return Unauthorized();
            }


            var claims = new List<Claim>
    {
        new Claim("Email", loginRequest.email),
        new Claim(ClaimTypes.Role, systemAccount.Role.ToString())
    };

            var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_configuration["Jwt:Key"]));
            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

            var token = new JwtSecurityToken(
                issuer: _configuration["Jwt:Issuer"],
                audience: _configuration["Jwt:Audience"],
                claims: claims,
                expires: DateTime.Now.AddDays(1),
                signingCredentials: creds);

            return Ok(new JwtSecurityTokenHandler().WriteToken(token));
        }
    }
}
