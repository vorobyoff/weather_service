package ru.dbeaver.weather_service.services.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@Transactional(propagation = REQUIRES_NEW)
public class Transactor {

    public <R> R exec(final Supplier<R> s) {
        return s.get();
    }

    public void run(final Runnable r) {
        r.run();
    }
}