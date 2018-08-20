package zp.common;

import zp.common.repository.PageElement;
import zp.common.repository.RepositoryContext;
import zp.common.repository.WebPage;
import zp.common.repository.WebRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@SpringBootApplication
@ComponentScan(basePackages = {"zp.common"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Jaxb2Marshaller jax2Marshaller() {
        Jaxb2Marshaller jax2Marshaller = new Jaxb2Marshaller();
        jax2Marshaller.setClassesToBeBound(WebPage.class, PageElement.class, WebRepository.class);
        return jax2Marshaller;
    }

    @Bean()
    RepositoryContext repositoryContext() {
        RepositoryContext repositoryContext = new RepositoryContext();
        repositoryContext.setRepoPath(new ClassPathResource("repository.xml"));
        repositoryContext.setJax2Marshaller(jax2Marshaller());
        repositoryContext.loadRepository();

        return repositoryContext;
    }
}
