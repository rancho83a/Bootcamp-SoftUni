type UserData = {
    newName: string;
    newAge: number
}

enum UserRole {
    Admin, Client
}

interface IUserNewData {
    newName: string;
    newAge: number
}

class Person {

    constructor(
        public name: string, 
        private age: number,
        public role: UserRole
        ) { }

    getAge() {
        return this.age;
    }

    setData(newData: IUserNewData){
        this.name=newData.newName;
        this.age=newData.newAge;
    }

}

const ivan = new Person("Ivan", 33, UserRole.Admin);
const newData: IUserNewData = {newAge=20,newName="ivan2"}
ivan.setData(newData); 

ivan.