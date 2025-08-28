package org.example;
import org.springframework.boot.CommandlineRunner;
import org.springframework.stereotype.Component;

@Component
public class AddOkhttp implements CommandlineRunner{
    @Override
    public void run(String... args) throws Exception{
        System.out.println("line runner working");
    }
}
