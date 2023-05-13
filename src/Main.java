

import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;
import builders.StudentsBuilder;
import entities.Studant;


public class Main {

    public static void main(String[] args) {
        
        var allStudents = StudentsBuilder.getAllStudents();

        Scanner scanner = new Scanner(System.in);

        System.out.println("1- 1º questão\n2- 2º questão\n3- 3º questão\n4- 4º questão\n5- 5º questão\n6- 6º questão\n7- 7º questão");
        var res = scanner.nextInt();

        switch  (res) {
            case 1: 
            
            var passedStudents = allStudents.stream()
            .filter(student -> student.getAverage() >= 7.0f)
            .collect(Collectors.toList());

            for (var student : passedStudents) {
            System.out.printf("%d - %s : Média = %.2f\n", student.getCode(), student.getName(), student.getAverage());
            }

            break;

            case 2:

            for (Studant student : allStudents) {
                float average = (student.getTestOne() + student.getTestTwo() + student.getTestThree()) / 3.0f;
                if (average < 7.0f) {
                    float missingGrade = 7.0f - average;
                    System.out.printf("%d - %s : Média = %.2f (Faltou = %.2f)\n", student.getCode(), student.getName(), average, missingGrade);
                }
            }

            break;

            case 3:

            for (Studant student : allStudents) {
                if (student.getTestOne() == 10 || student.getTestTwo() == 10 || student.getTestThree() == 10) {
                    System.out.println(student.getCode() + " - " + student.getName());
                }
            }
            
            break;

            case 4: 


            Studant studentWithLowestGrade = allStudents.get(0);
    
            for (Studant student : allStudents) {
                if (student.getAverage() < studentWithLowestGrade.getAverage()) {
                    studentWithLowestGrade = student;
                } else if (student.getAverage() == studentWithLowestGrade.getAverage()) {
                    System.out.println(studentWithLowestGrade.getCode() + " - " + studentWithLowestGrade.getName() + " : Nota = " + studentWithLowestGrade.getAverage());
                    System.out.println(student.getCode() + " - " + student.getName() + " : Nota = " + student.getAverage());
                }
            }
            
            System.out.println(studentWithLowestGrade.getCode() + " - " + studentWithLowestGrade.getName() + " : Nota = " + studentWithLowestGrade.getAverage());

            break;


            case 5:
            
            // Ordena a lista de estudantes em ordem decrescente de nota
            var sortedStudents = allStudents.stream()
            .sorted(Comparator.comparingDouble(Studant::getAverage).reversed())
            .collect(Collectors.toList());

            // Cria uma lista com os top 3 alunos
            var topStudents = sortedStudents.stream()
                    .limit(3)
                    .collect(Collectors.toList());

            // Imprime a lista dos top 3 alunos no formato desejado
            System.out.println("Top 3 Alunos:");
            for (int i = 0; i < topStudents.size(); i++) {
                var student = topStudents.get(i);
                var position = i + 1;

                // Verifica se há empate na nota e inclui todos os alunos com a mesma nota na mesma posição
                if (i > 0 && student.getAverage() == topStudents.get(i - 1).getAverage()) {
                    System.out.printf("%dº - %s : Nota = %.1f;%n", position - 1, student.getName(), student.getAverage());
                } else {
                    System.out.printf("%dº - %s : Nota = %.1f;%n", position, student.getName(), student.getAverage());
                }
            }
            
            break;

            case 6:

            var copyOfStudents = allStudents.stream().collect(Collectors.toList());

            // ordena a cópia da lista em ordem crescente de notas
            copyOfStudents.sort(Comparator.comparingDouble(Studant::getAverage));
    
            // seleciona as três primeiras notas da lista ordenada
            var lowestGrades = copyOfStudents.stream()
                    .mapToDouble(Studant::getAverage)
                    .distinct()
                    .limit(3)
                    .toArray();
    
            
            var lowestGradeStudents = allStudents.stream()
                    .filter(s -> {
                        for (double grade : lowestGrades) {
                            if (s.getAverage() == grade) {
                                return true;
                            }
                        }
                        return false;
                    })
                    .collect(Collectors.toList());
    
           
            for (int i = 0; i < lowestGradeStudents.size(); i++) {
                var student = lowestGradeStudents.get(i);
                System.out.printf("%d - %s: Nota = %.1f%n", i + 1, student.getName(), student.getAverage());
            }


            break;


            case 7: 

                    // Recupera os alunos que passaram de ano
                var passsedStudents = allStudents.stream()
                .filter(student -> student.getAverage() >= 7.0f)
                .collect(Collectors.toList());

                // Calcula a média de todos os alunos
                var allStudentsAverage = allStudents.stream()
                        .mapToDouble(Studant::getAverage)
                        .average()
                        .orElse(0.0);

                // Ordena a lista de alunos aprovados por média, em ordem decrescente
                passsedStudents.sort(Comparator.comparing(Studant::getAverage).reversed());

                // Exibe os dados dos alunos aprovados, em formato específico
                passsedStudents.forEach(student ->
                        System.out.printf("%d - %d - %s: Média = %.2f%n",
                                passsedStudents.indexOf(student) + 1,
                                student.getCode(),
                                student.getName(),
                                student.getAverage()));

                System.out.printf("Média de todos os alunos: %.2f%n", allStudentsAverage);
         

            break;

        }





        // Agora vamos as atividades
        /*

        1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
            - Exiba os dados nesse formato: <código> - <nome> : Média = <nota>

     
        2. Recupere da lista os alunos que não passaram de ano.
            - Exiba os dados nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)



        3. Traga os alunos que tiraram a nota máxima (nota 10).
            - Exiba os dados nesse formato: <código> - <nome>


        4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.
            - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>


        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>


        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>


        7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>

         */
        
    }



    
}
