package com.skyeng.test

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
@Singleton
class SchedulersFacade @Inject constructor() {
    /**
     * Пул потоков для выполнения "долгих" операций. Рекомендуется использовать для запросов
     * на сервер либо других потенциально долгих операций.
     *
     * @return пул потоков
     */
    fun io() : Scheduler {
        return Schedulers.io()
    }

    /**
     * Пул потков для "тяжелых" локальных вычислений. Рекомендуется использовать
     * для математических расчетов либо других операций.
     *
     * @return пул потоков
     */
    fun computation() : Scheduler {
        return Schedulers.computation()
    }

    /**
     * Возвращает планировщик для основного потока Андроид системы. Необходим чтобы общаться
     * с пользовательским интерфесом системы в основном потоке.
     *
     * @return планировщик андроид
     */
    fun ui() : Scheduler {
        return AndroidSchedulers.mainThread()
    }
}