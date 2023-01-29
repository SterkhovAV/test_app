package ru.sterkhovav.test_app.service


import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.AbstractHttpMessageConverter
import org.springframework.stereotype.Service
import ru.sterkhovav.test_app.dao.models.OrderRequest
import javax.xml.XMLConstants
import javax.xml.bind.*
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory


@Service
class OrderRequestConverter : AbstractHttpMessageConverter<OrderRequest>(MediaType.APPLICATION_XML) {
    private val schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)

    override fun supports(clazz: Class<*>): Boolean {
        return clazz == OrderRequest::class.java
    }

    override fun readInternal(clazz: Class<out OrderRequest>, inputMessage: HttpInputMessage): OrderRequest {
        return try {
            val jaxbContext = JAXBContext.newInstance(OrderRequest::class.java)
            val jaxbUnmarshaller = jaxbContext.createUnmarshaller()
            jaxbUnmarshaller.eventHandler = OrdersRequestValidator()
            jaxbUnmarshaller.schema =
                schemaFactory.newSchema(StreamSource(OrderRequestConverter::class.java.getResourceAsStream("/orderRequest.xsd")))
            jaxbUnmarshaller.unmarshal(inputMessage.body) as OrderRequest
        } catch (e: UnmarshalException) {
            throw ValidationException(e.message ?: "Unexpected error")
        }
    }

    override fun writeInternal(p0: OrderRequest, p1: HttpOutputMessage) {
        TODO("Пока только принимаем")
    }
}


class OrdersRequestValidator : ValidationEventHandler {
    override fun handleEvent(event: ValidationEvent): Boolean {
        /*
        Вопрос 1.
        Severity может принимать следующие значения
        public static final int WARNING     = 0;
        public static final int ERROR       = 1;
        public static final int FATAL_ERROR = 2;

        Из документации
        Любое отклонение от требований корректности формирования (well-formed) считается фатальной ошибкой (fatal error).
        Отклонение от требований валидности (valid) считается лишь ошибкой (error).

        Показалось странным что если создать несоотвествующий тег то будет fatal error, а если вместо числа подставить
        что-то типа "69d" выдает error.
        Ожидал что будет наоборот))

        Что вызывает WARNING?

        Вопрос 2.
        В xsd нельзя реализовать проверку времени (запрет запросов с временем из будующего)?
         */

        throw UnmarshalException("Error in line ${event.locator.lineNumber}. ${event.linkedException.message}")
        TODO("Разобраться что может вызвать WARNING(event.severity==0) и при необходимости отдельная обработка")
    }


}

