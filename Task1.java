import java.util.Arrays;
import java.util.LinkedList;

public class Task1 {
/*
* Этот код выполняет все 5 задач:
* 1. задавати модуль m, за яким будуть вестись розрахунки  - модуль можно задать для всех задач, где нужно искать значение по модулю.
* 2. розв’язувати рівняння виду  a mod m = x - чтобы найти x нужно использовать ф-ию mod.
* Чтобы увидеть результат в консоли используйте System.out.println();
* 3. розв’язувати рівняння виду a^b mod m = x - чтобы найти x нужно использовать ф-ию mod,
* существеут перегруженная версия, где на вход подается 3 аргумента (a,b,m).
* Чтобы увидеть результат в консоли используйте System.out.println();
* 4. розв’язувати рівняння виду  a*x ≡ b mod m. Для это исполузеется ф-ия reverse (a,b,m)
* Чтобы увидеть результат в консоли используйте System.out.println();
* 5. генерувати просте число у діапазоні від A до B - используем ф-ию randomPrime(), на вход подается диапозон числе от A до B
* Чтобы увидеть результат в консоли используйте System.out.println();

 *
* */
    public static void main(String[] args) {

        System.out.println(mod(6,100)); // Пример a mod m = x
        System.out.println(mod(6,5,100));// Пример a^b mod m = x
        System.out.println(reverse(13,2,53));// Пример a*x ≡ b mod m
        System.out.println(randomPrime(6,100)); // Пример генерирования простого числа в диопозоне



    }
    /* розвязывает уравнение вида a mod m = x. Проще говоря находит модуль о заданого числа по заданному модулю.
        на ход ф-ии податся первым аргуменом число (a), вторым - модуль (m).
        Ф-ия работает как для положительных чначений числа (a), так и для отрицательных.
        Не обрабатывает исключения, такие как m<0.
        Возвращает результат x.
    * */
    public static int mod (int a, int m){
        if(a>=0) {
            return a % m;
        }
        else {
           double tmp =(double) a/m;
           int b = (int) Math.floor(tmp);
            return a-(b*m);
        }
    }
    /*
    * розвязывает уравнение вида a^b mod m = x. В цикле while считается результат, пока степень не будет равна 0.
    * Степень делится на 2 каждый круг. Так же если текущая степень делится на 2 с остатком,то если степень
    * представить в бинарном виде мы увидим 1.
    * */
    public static int mod (int a, int b, int m){

        int result = 1;
        while (b!=0){
            if (b%2 == 1) {
                result = (result * a) % m;
            }
                b = b/2;
                a = (a * a)%m;
        }
        return result;
    }
/*Расчет найбольшего общего делителя по алгоритму Эвклида*/
    public static int nod (int a, int b){
        if (a==0){
            return b;
        }
        while (b!=0){
            if (a>b){
                a = a-b;
            }else{
                b = b-a;
            }
        }
        return a;
    }
/*Расчет ф-ии Эйлера */
    public static int phi (int n){
        int result = 1;
        for (int i =2; i<n; i++){
            if(nod(i,n) == 1){
                result++;
            }
        }
        return result;
    }
/* Розвязыввает уравнение типа a*x ≡ b mod m. Для этого на вход подаутся аргументы a,b и m. Уравнение решается, если НОД(a,m) = 1.
Далее a*x ≡ b mod m преобразовывается в x ≡  a^φ(n)-1 (mod m). φ(n)-1 находится функцей phi(). А a^φ(n)-1 считается по функции
mod(a,b,m) (a^b mod m = x)
* */
    public static int reverse (int a, int b, int m){
        int x = -1;
        if (nod(a,m) == 1){
            x = (b *(mod(a,phi(m)-1,m)))%m;
            return x;
        }
        return x;
    }
    /*На выходе получаем все числа до числа n, которые являются простыми. Используется решето Эратосфена*/
    public static LinkedList<Integer> eratosphene ( int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        LinkedList<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
/*Генерация простого числа в диапозоне от A до B. Сначала генерируется список простых чисел до B исполузуя решето Ератосфена.
* Далее находим простое число начала диапозона a. И учитывая диапозон A-B вынадается случайное простое число */
    public static int randomPrime(int a, int b){
        LinkedList<Integer> primeNumbers = eratosphene(b);
       while (!isPrime(a)){
           a++;
       }
       a = primeNumbers.indexOf(a);
        int maxPos = primeNumbers.size()-a;
        double random = Math.random();
        int randomPos = (int) (random * maxPos)+a;
        return primeNumbers.get(randomPos)  ;

    }
    /*Проверка числа на простое*/
    private static boolean isPrime(int number) {
        for (int i = 2; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }



}
