package techlearn;
import techlearn.composite.*;
import techlearn.iterator.Iterator;
import techlearn.login.LoggerService;

public class Main {
    public static void main(String[] args) {
        LoggerService logger = LoggerService.getInstance();
        logger.log("Iniciando montagem da estrutura de cursos...");

        Modulo cursoEngenharia = new Modulo("Engenharia de Software Moderna");
        Modulo moduloOO = new Modulo("Módulo 1: Orientação a Objetos Avançada");
        moduloOO.adicionar(new Aula("Aula 1.1: Coesão e Acoplamento", 45));
        moduloOO.adicionar(new Aula("Aula 1.2: Interfaces e Polimorfismo", 60));

        Modulo moduloPadroes = new Modulo("Módulo 2: Padrões de Projeto (GoF)");
        moduloPadroes.adicionar(new Aula("Aula 2.1: Singleton na Prática", 30));
        moduloPadroes.adicionar(new Aula("Aula 2.2: Composite Hierárquico", 50));
        moduloPadroes.adicionar(new Aula("Aula 2.3: Iterator para Coleções", 40));

        cursoEngenharia.adicionar(moduloOO);
        cursoEngenharia.adicionar(moduloPadroes);

        logger.log("Estrutura montada com sucesso. Exibindo detalhes da ementa:");

        cursoEngenharia.exibirDetalhes();

        System.out.println("\n===================================================");
        System.out.println(" Varredura do Curso Principal via Iterator:");
        System.out.println("==================================================");

        Iterator<ComponenteCatalogo> iterator = cursoEngenharia.criarIterator();
        while (iterator.hasNext()) {
            ComponenteCatalogo subItem = iterator.next();
            logger.log("Navegando no Item Raiz: " + subItem.getNome() + " | Duração acumulada: " + subItem.getDuracaoMinutos() + " min");
        }

        System.out.println("\n===================================================");
        System.out.println(" Varredura do Curso Usando Filtro:");
        System.out.println("==================================================");
        filtrarAulasCurtas(cursoEngenharia);

        logger.log("\nExecução da atividade finalizada.");
    }

    public static void filtrarAulasCurtas(ComponenteCatalogo componente) {
        // realiza o filtro de fato
        if (componente instanceof Aula && componente.getDuracaoMinutos() <= 45) {
            componente.exibirDetalhes();
        }

        // iterator para ver se tem filho, se tiver chama funcao recursivamente
        Iterator<ComponenteCatalogo> iterator = componente.criarIterator();

        while (iterator.hasNext()) {
            ComponenteCatalogo subItem = iterator.next();
            filtrarAulasCurtas(subItem);
        }
    }
}