angular.module('app', [])
    .controller('VaccineController', function ($http, $scope) {
        var vm = this;
        vm.appointment = {};
        vm.selectedDate = '';
        vm.timeslots = []
        vm.isLogged = false;

        vm.months = [
            { name: 'January', value: 1 },
            { name: 'February', value: 2 },
            { name: 'March', value: 3 },
            { name: 'April', value: 4 },
            { name: 'May', value: 5 },
            { name: 'June', value: 6 },
            { name: 'July', value: 7 },
            { name: 'August', value: 8 },
            { name: 'September', value: 9 },
            { name: 'October', value: 10 },
            { name: 'November', value: 11 },
            { name: 'December', value: 12 }
        ];

        vm.login = function () {

            $http.get('http://localhost:8080/login', {
                params: {
                    amka: vm.amka,
                    password: vm.password
                }
            }).then(function (response) {
                if (response.data.amka == "Wrong password") {
                    alert('wrong password');
                }
                if (response.data.amka == "Wrong amka") {
                    alert('Wrong amka');
                }
                if (response.data.amka != "Wrong password" && response.data.amka != "Wrong amka") {
                    console.log("good")
                    vm.isLogged = true;

                    if (response.data.email != null) { // Is citizen
                        vm.citizen = response.data;
                        getAppointment();
                    }
                    if (response.data.email == null) { // Is doctor
                        vm.doctor = response.data;
                        getAppointments();
                        getTimeslots();
                    }


                }

            });
        };

        vm.searchAvailability = function () {
            $http.get('http://localhost:8080/timeslot/searchByDate', {

                params: {
                    date: vm.selectedDate.toISOString().slice(0, 10)
                }
            }).then(function (response) {
                console.log(response.data)
                vm.timeslots = response.data;
            });
        };

        vm.searchAvailabilityByMonth = function () {
            console.log(vm.selectedMonth.value)

            $http.get('http://localhost:8080/timeslot/searchByMonth', {
                params: {
                    month: vm.selectedMonth.value
                }
            }).then(function (response) {
                console.log(response.data)
                vm.timeslots = response.data;
            });
        };


        vm.scheduleAppointment = function (timeslot) {
            $http.post('http://localhost:8080/appointments/createAppointment', {
                date: vm.selectedDate,
                timeslot: timeslot,
                citizen: vm.citizen,
                doctor: timeslot.doctor
            }).then(function (response) {
                vm.appointment = response.data;
                alert('Your appointment has been scheduled for ' + vm.appointment.date + ' at ' +
                    timeslot.startingHour);
            });
        };

        function getAppointment() {

            $http.get('http://localhost:8080/appointments/citizen', {
                params: {
                    citizenId: vm.citizen.id
                }
            }).then(function (response) {
                console.log(response.data)
                vm.appointment = response.data;
            });
        }

        vm.createTimeslot = function () {
            $http.post('http://localhost:8080/timeslot/create', {
                doctor: vm.doctor,
                date: vm.selectedDate.toISOString().slice(0, 10),
                startingHour: vm.selectedDate.toISOString().slice(11, 13),
                startingMinute: vm.selectedDate.toISOString().slice(14, 16),
                year: vm.selectedDate.toISOString().slice(0, 4),
                month: vm.selectedDate.toISOString().slice(5, 7),
                day: vm.selectedDate.toISOString().slice(8, 10),
            }).then(function (response) {
                console.log(response.data)
                vm.timeslots.push(response.data);
            });
        }

        function getTimeslots() {
            $http.get('http://localhost:8080/timeslot/getByDoctorId', {
                params: {
                    doctorId: vm.doctor.id
                }
            }).then(function (response) {
                console.log(response.data)
                vm.timeslots = response.data;
            });
        };

        function getAppointments() {

            $http.get('http://localhost:8080/appointments/doctor', {
                params: {
                    doctorId: vm.doctor.id,
                    pageSize: 1,
                    pageNumber: vm.currentPage
                }

            }).then(function (response) {

                vm.appointments = response.data;

                vm.pagedAppointments = response.data;
            });
        };

        vm.canceltimeslot = function (timeslot, index) {
            $http.delete('http://localhost:8080/timeslot/cancelTimeslot', {
                params: { timeslotId: timeslot.id }
            }).then(function (response) {
                alert("Timeslot canceled");
                vm.timeslots.splice(index, 1)

            });
        };
        vm.cancelAppointmentCitizen = function (appointmentId, timeslotId) {
            $http.delete('http://localhost:8080/appointments/cancelAppointment', {
                params: { appointmentId: appointmentId, timeslotId: timeslotId }
            }).then(function (response) {
                vm.citizen.timesCanceled++;
                console.log(3 - vm.citizen.timesCanceled)
                var numberOfCancellationsLeft = 3 - vm.citizen.timesCanceled;
                alert("Appointment canceled. You can cancel " + numberOfCancellationsLeft + " more times");
                vm.appointment = null;
            });
        };


        vm.cancelAppointment = function (appointmentId, timeslotId, index) {
            cancelAppointment(appointmentId, timeslotId, index);
        }

        function cancelAppointment(appointmentId, timeslotId, index) {
            $http.delete('http://localhost:8080/appointments/cancelAppointment', {
                params: { appointmentId: appointmentId, "timeslotId": timeslotId }
            }).then(function () {
                vm.appointment = null;
                

                getAppointments();
                alert("Vaccination Confirmed");
                
            });
        };

        vm.confirmVaccination = function (citizenId, index) {
            $http.post('http://localhost:8080/vaccination/save', {
                doctor: vm.doctor,
                date: vm.appointments[index].date,
                citizenId: citizenId
            }).then(function (response) {
                cancelAppointment(vm.appointments[index].id, vm.appointments[index].timeslot.id,
                    index);
            });
        };

        vm.appointmentsPerPage = 10;
        vm.currentPage = 0;

        vm.previousPage = function () {
            if (vm.currentPage > 0) {
                vm.currentPage--;
            }
            console.log(vm.currentPage)

            let startIndex = (vm.currentPage - 1) * vm.appointmentsPerPage;
            // vm.pagedAppointments = vm.appointments.slice(startIndex, startIndex + vm.appointmentsPerPage);
            getAppointments();
        };

        vm.nextPage = function () {
            if (vm.appointments.length > 0) {
                vm.currentPage++;
            }

            console.log(vm.currentPage)
            let startIndex = (vm.currentPage - 1) * vm.appointmentsPerPage;
           
            // vm.pagedAppointments = vm.appointments.slice(startIndex, startIndex + vm.appointmentsPerPage);
            getAppointments();
        };


    });