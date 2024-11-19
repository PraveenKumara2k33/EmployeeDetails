import { useEffect, useState } from "react"
import { createEmployee, getEmployee, updateEmployee } from "../Services/EmployeeService"
import {useNavigate, useParams} from 'react-router-dom';

const EmployeeComponent = () => {
    const[firstName, setFirstName] = useState('')
    const[lastName, setLastName] = useState('')
    const[email, setEmail] = useState('')

    const {id} = useParams();

    const[errors, setErrors] = useState({
        firstName:'',
        lastName:'',
        email:''
    })

    const navigator = useNavigate();

    useEffect(()=>{
        if(id){
            getEmployee(id).then((response)=>{
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setEmail(response.data.email);
            }).catch(error =>{
                console.error(error);
                
            })
        }
    },[id])

    function saveOrUpdateEmployee(e){
        e.preventDefault();

        if(vaildateForm()){

            const employee = {firstName, lastName, email}
            console.log(employee);

            if(id){
                updateEmployee(id, employee).then((response)=>{
                    console.log(response.data);
                    navigator('/employees');
                }).catch(error =>{
                    console.error(error);
                })
            }else{
                createEmployee(employee).then( (response) => {
                    console.log(response.data);
                    navigator('/employees')
                }).catch(error =>{
                    console.error(error);
                })
            }
        }
        
    }

   
    function vaildateForm(){
        
        let vaild = true;
        const errorsCopy = {...errors}
        
        if(firstName.trim()){
            errorsCopy.firstName='';
        }else{
            errorsCopy.firstName='First Name is required';
            vaild = false
        }

        if(lastName.trim()){
            errorsCopy.lastName='';
        }else{
            errorsCopy.lastName='Last Name is required';
            vaild = false
        }

        if(email.trim()){
            errorsCopy.email='';
        }else{
            errorsCopy.email='Email is required';
            vaild = false
        }

        setErrors(errorsCopy);
        return vaild;
    }

    function pageTitle(){
        if(id){
            return <h2 className="text-center">Update Employee</h2>
        }else{
            return <h2 className="text-center">Add Employee</h2>
        }
    }
  return (
    <div>
        <div className="container">
            <div className="row">
                <div className="card mt-5 col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form action="">
                            <div className="form-group mb-2">
                                <label htmlFor="" className="form-label">First Name:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter Employee First Name"
                                    name="firstName"
                                    value={firstName}
                                    className={`form-control ${errors.firstName ? 'is-invalid':''}`}
                                    onChange={(e) => setFirstName(e.target.value)} 
                                />
                                {errors.firstName && <div className="invalid-feedback">{errors.firstName}</div>}
                            </div>

                            <div className="form-group mb-2">
                                <label htmlFor="" className="form-label">Last Name:</label>
                                <input 
                                    type="text"
                                    placeholder="Enter Employee Last Name"
                                    name="LastName"
                                    value={lastName}
                                    className={`form-control ${errors.lastName ? 'is-invalid':''}`}
                                    onChange={(e) => setLastName(e.target.value)} 
                                />
                                {errors.lastName && <div className="invalid-feedback">{errors.lastName}</div>}
                            </div>

                            <div className="form-group mb-2">
                                <label htmlFor="" className="form-label">Email:</label>
                                <input 
                                    type="email"
                                    placeholder="Enter Employee Email"
                                    name="Email"
                                    value={email}
                                    className={`form-control ${errors.email ? 'is-invalid':''}`}
                                    onChange={(e) => setEmail(e.target.value)} 
                                />
                                {errors.email && <div className="invalid-feedback">{errors.email}</div>}
                            </div>

                            <button className="btn btn-success" onClick={saveOrUpdateEmployee}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
  )
}

export default EmployeeComponent