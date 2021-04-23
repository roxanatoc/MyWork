using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1

{
    class User
    {
        private String username;
        private String password;
        private String name;
        private String role;
        public User(String username, String password, String name, String role)
        {
            this.username = username;
            this.password = password;
            this.name = name;
            this.role = role;
        }

        public String getRole()
        {
            return this.role;
        }
    }
}
