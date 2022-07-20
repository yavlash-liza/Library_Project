(function () {
    'use strict'

    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {

            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
            form.querySelectorAll('input').forEach(function (input) {
                input.addEventListener('input', function () {
                    form.querySelectorAll('.signup-unique').forEach(function (message) {
                        message.remove();
                    })
                    input.classList.remove('is-invalid')
                    input.classList.remove('is-valid')
                })
            })
        })
})()