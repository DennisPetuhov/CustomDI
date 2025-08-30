package com.example.customdi.di

import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.isAccessible


@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Inject

// Провайдер для кастомных модулей
fun interface Provider<T> {
    fun get(): T
}

class CustomDI {
    private val singletons = mutableMapOf<KClass<*>, Any>()
    private val providers = mutableMapOf<KClass<*>, Provider<*>>()
    fun <T : Any> registerProvider(type: KClass<T>, provider: Provider<out T>) {
        providers[type] = provider
    }

    inline fun <reified T : Any> getInstance(): T = getInstance(T::class)

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getInstance(type: KClass<T>): T {
        // 1) Если уже создан singleton — возвращаем
        singletons[type]?.let { return it as T }

        // 2) Кастомный провайдер
        providers[type]?.let {
            val inst = (it as Provider<T>).get()
            singletons[type] = inst
            return inst
        }
        // 3) Выбор конструктора: @Inject или primary/default
        val ctor = type.constructors.firstOrNull{
            it.findAnnotation<Inject>() != null
        }?: type.primaryConstructor ?:type.constructors.firstOrNull{it.parameters.isEmpty()}
        ?:throw IllegalArgumentException("No constructor found for ${type.simpleName}")
        ctor.isAccessible=true

      val args=  ctor.parameters.associateWith {
            getInstance(it.type.classifier as KClass<*>)
       }
        val obj = ctor.callBy(args)
        //  Внедрение полей @Inject
        // 7) Кэшируем и возвращаем
        singletons[type] = obj
        return obj

    }
}