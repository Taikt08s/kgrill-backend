using Repositories;
using Repositories.Models;
using Services;

namespace PRN221_PE_SU24_082408_VoVanTinhSE173112
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.
            builder.Services.AddRazorPages();
            builder.Services.AddScoped<AccountService>();
            builder.Services.AddScoped<AccountRepository>();
            builder.Services.AddScoped<Euro2024DbContext>();
            builder.Services.AddSession();

            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseSession();
            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();
            app.MapFallbackToPage("/Login");
            app.MapRazorPages();

            app.Run();
        }
    }
}
