using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;
using WindowsFormsApp1.DAL.Interfaces;

namespace WindowsFormsApp1.BL
{
    class UserService
    {
        IUserDAO _repository;

        public UserService(IUserDAO repository)
        {
            _repository = repository;
        }

        public User login(String username, String password)
        {
            UserDAOMSSQL usersDAL = UserDAOMSSQL.getInstance();
            User user = usersDAL.getUser(username, getMd5Hash(password));

            return user;
        }

        static string getMd5Hash(string input)

        {
            MD5 md5Hasher = MD5.Create();
            byte[] data = md5Hasher.ComputeHash(Encoding.Default.GetBytes(input));
            StringBuilder sBuilder = new StringBuilder();
            for (int i = 0; i < data.Length; i++)
            {
                sBuilder.Append(data[i].ToString("x2"));
            }

            return sBuilder.ToString();

        }

        public void addEmployee(string username, string password, string name)
        {
            _repository.addEmployee(username, getMd5Hash(password), name);
        }
    }
}
