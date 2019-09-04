package ru.stacy.soapapplication.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.stacy.soapapplication.repositories.CountryRepository;
import stacy.soap_application.GetCountryRequest;
import stacy.soap_application.GetCountryResponse;

/*
    @Endpoint регистрирует класс Spring WS как потенциальный кандидат для обработки входящих SOAP сообщений

    @PayloadRoot используется Spring WS для выбора метода обработчика на основе namespace и localPart сообщения

    @RequestPayload указывает на то, что входящее сообщение будет сопоставлено параметру request метода

    @ResponsePayload создает соответствующее значение возвращаемому значению полезной части ответа
 */

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://stacy/soap-application";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }
}
