$(async function (id) {
    await editUser(id);
});

async function editUser(id) {

    const form = document.forms["editUserForm"];
    $('#editUserRoles').empty();

    await allRoles().then(roles => {
        roles.forEach(role => {
            let option = new Option(role.roleName.substring(5), role.id);
            option.setAttribute("id", role.roleName);
            $('#editUserRoles').append(option);
        })
    })

    await showUserById(id).then(user => {
        $('#editUserId').val(user.id)
        $('#editUserName').val(user.name)
        $('#editUserSurname').val(user.surname)
        $('#editUserAge').val(user.age)
        $('#editUserPassword').val(user.password)
        $('#editUserEmail').val(user.email)

        user.roles.forEach(role => {console.log(role.roleName.substring(5));
            document.getElementById(role.roleName).selected = true;
        })
    })

    form.addEventListener('submit', editProcess);

    async function editProcess(event) {
        event.preventDefault();

        let edituser = await userData(form);

        fetch("/api/admin/" + id, {
            method: 'PATCH',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'Referer': null
            },
            body: edituser
        }).then(() => {
            $('#editFormCloseButton').click();
            showUsersTable();
        })
    }
}