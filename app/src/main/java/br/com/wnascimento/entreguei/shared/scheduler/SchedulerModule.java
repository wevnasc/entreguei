package br.com.wnascimento.entreguei.shared.scheduler;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class SchedulerModule {

    @Provides
    @IOScheduler
    Scheduler providerSchedulerExecutor() {
        return Schedulers.io();
    }

    @Provides
    @MainScheduler
    Scheduler providerSchedulerMain() {
        return AndroidSchedulers.mainThread();
    }
}
