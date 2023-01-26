let x = 8;
let y = 12;
let z = 18;
const w = 'private';
console.log(x,y,z,w);

$(async function () {
    await authUser();
});

async function authUser() {

        fetch("/api/user", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            }
        })
            .then((response) => response.json())
            .then(data => {
                $('#authUser').append(data.mail);
                let roles = data.roles.map(role => " " + role.roleName.substring(5));
                $('#authUserRoles').append(roles);
                let user = `$(
                <tr> 
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.lastName}</td>
                    <td>${data.age}</td>
                    <td>${data.mail}</td>
                    <td>${roles}</td>
                </tr>    
                )`;
            $('#authUserPanel').append(user);
        })
}

