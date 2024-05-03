package aoizora;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class CrptApi
{
    private final String apiUrl;
    private final FrequencyCounter counter;
    private final int requestLimit;

    public CrptApi(String apiUrl, int requestLimit, int timeInterval, TimeUnit timeUnit)
    {
        this.apiUrl = apiUrl;
        this.counter = new FrequencyCounter(timeInterval, timeUnit);
        this.requestLimit = requestLimit;
    }

    static class Product
    {
        private String certificate_document;
        private String certificate_document_date;
        private String certificate_document_number;
        private String owner_inn;
        private String producer_inn;
        private String production_date;
        private String tnved_code;
        private String uit_code;
        private String uitu_code;

        public Product(String certificate_document,
                       String certificate_document_date,
                       String certificate_document_number,
                       String owner_inn, String producer_inn,
                       String production_date, String tnved_code,
                       String uit_code,
                       String uitu_code)
        {
            this.certificate_document = certificate_document;
            this.certificate_document_date = certificate_document_date;
            this.certificate_document_number = certificate_document_number;
            this.owner_inn = owner_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.tnved_code = tnved_code;
            this.uit_code = uit_code;
            this.uitu_code = uitu_code;
        }

        public String getCertificate_document()
        {
            return certificate_document;
        }

        public void setCertificate_document(String certificate_document)
        {
            this.certificate_document = certificate_document;
        }

        public String getCertificate_document_date()
        {
            return certificate_document_date;
        }

        public void setCertificate_document_date(String certificate_document_date)
        {
            this.certificate_document_date = certificate_document_date;
        }

        public String getCertificate_document_number()
        {
            return certificate_document_number;
        }

        public void setCertificate_document_number(String certificate_document_number)
        {
            this.certificate_document_number = certificate_document_number;
        }

        public String getOwner_inn()
        {
            return owner_inn;
        }

        public void setOwner_inn(String owner_inn)
        {
            this.owner_inn = owner_inn;
        }

        public String getProducer_inn()
        {
            return producer_inn;
        }

        public void setProducer_inn(String producer_inn)
        {
            this.producer_inn = producer_inn;
        }

        public String getProduction_date()
        {
            return production_date;
        }

        public void setProduction_date(String production_date)
        {
            this.production_date = production_date;
        }

        public String getTnved_code()
        {
            return tnved_code;
        }

        public void setTnved_code(String tnved_code)
        {
            this.tnved_code = tnved_code;
        }

        public String getUit_code()
        {
            return uit_code;
        }

        public void setUit_code(String uit_code)
        {
            this.uit_code = uit_code;
        }

        public String getUitu_code()
        {
            return uitu_code;
        }

        public void setUitu_code(String uitu_code)
        {
            this.uitu_code = uitu_code;
        }
    }

    static class Description
    {
        private String participantInn;

        public Description(String participantInn)
        {
            this.participantInn = participantInn;
        }

        public String getParticipantInn()
        {
            return participantInn;
        }

        public void setParticipantInn(String participantInn)
        {
            this.participantInn = participantInn;
        }
    }

    static class Document
    {

        private Description description;
        private String doc_id;
        private String doc_status;
        private String doc_type;
        private boolean importRequest;
        private String owner_inn;
        private String participant_inn;
        private String producer_inn;
        private String production_date;
        private String production_type;
        private List<Product> products;
        private String reg_date;
        private String reg_number;

        public Document(Description description,
                        String doc_id,
                        String doc_status,
                        String doc_type,
                        boolean importRequest,
                        String owner_inn,
                        String participant_inn,
                        String producer_inn,
                        String production_date,
                        String production_type,
                        List<Product> products,
                        String reg_date,
                        String reg_number) {
            this.description = description;
            this.doc_id = doc_id;
            this.doc_status = doc_status;
            this.doc_type = doc_type;
            this.importRequest = importRequest;
            this.owner_inn = owner_inn;
            this.participant_inn = participant_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.production_type = production_type;
            this.products = products;
            this.reg_date = reg_date;
            this.reg_number = reg_number;
        }

        public Description getDescription()
        {
            return description;
        }

        public void setDescription(Description description)
        {
            this.description = description;
        }

        public String getDoc_id()
        {
            return doc_id;
        }

        public void setDoc_id(String doc_id)
        {
            this.doc_id = doc_id;
        }

        public String getDoc_status()
        {
            return doc_status;
        }

        public void setDoc_status(String doc_status)
        {
            this.doc_status = doc_status;
        }

        public String getDoc_type()
        {
            return doc_type;
        }

        public void setDoc_type(String doc_type)
        {
            this.doc_type = doc_type;
        }

        public boolean isImportRequest()
        {
            return importRequest;
        }

        public void setImportRequest(boolean importRequest)
        {
            this.importRequest = importRequest;
        }

        public String getOwner_inn()
        {
            return owner_inn;
        }

        public void setOwner_inn(String owner_inn)
        {
            this.owner_inn = owner_inn;
        }

        public String getParticipant_inn()
        {
            return participant_inn;
        }

        public void setParticipant_inn(String participant_inn)
        {
            this.participant_inn = participant_inn;
        }

        public String getProducer_inn()
        {
            return producer_inn;
        }

        public void setProducer_inn(String producer_inn)
        {
            this.producer_inn = producer_inn;
        }

        public String getProduction_date()
        {
            return production_date;
        }

        public void setProduction_date(String production_date)
        {
            this.production_date = production_date;
        }

        public String getProduction_type()
        {
            return production_type;
        }

        public void setProduction_type(String production_type)
        {
            this.production_type = production_type;
        }

        public List<Product> getProducts()
        {
            return products;
        }

        public void setProducts(List<Product> products)
        {
            this.products = products;
        }

        public String getReg_date()
        {
            return reg_date;
        }

        public void setReg_date(String reg_date)
        {
            this.reg_date = reg_date;
        }

        public String getReg_number()
        {
            return reg_number;
        }

        public void setReg_number(String reg_number)
        {
            this.reg_number = reg_number;
        }
    }

    public class FrequencyCounter
    {

        private final long monitoringInterval;

        private final int[] details;

        private final AtomicInteger currentCount = new AtomicInteger();

        private long startInterval;

        private int total;

        FrequencyCounter(long interval, TimeUnit unit)
        {
            this(interval, unit, 16);
        }

        FrequencyCounter(long interval, TimeUnit unit, int precision)
        {
            monitoringInterval = unit.toMillis(interval);

            if(monitoringInterval <= 0)
            {
                throw new IllegalArgumentException( "Interval mus be a positive value:" + interval );
            }

            details = new int[precision];
            startInterval = System.currentTimeMillis() - monitoringInterval;
        }

        public void increment()
        {
            checkInterval(System.currentTimeMillis());
            currentCount.incrementAndGet();
        }

        public int getCount()
        {
            long currentTime = System.currentTimeMillis();
            checkInterval(currentTime);
            long diff = currentTime - startInterval - monitoringInterval;

            double partFactor = (diff * details.length / (double)monitoringInterval);
            int part = (int)(details[0] * partFactor);
            return total + currentCount.get() - part;
        }

        private void checkInterval(final long time) {
            if( (time - startInterval - monitoringInterval) > monitoringInterval / details.length )
            {
                synchronized(details)
                {
                    long detailInterval = monitoringInterval / details.length;
                    while((time - startInterval - monitoringInterval) > detailInterval)
                    {
                        int currentValue = currentCount.getAndSet(0);
                        if((total | currentValue) == 0)
                        {
                            startInterval = time - monitoringInterval;
                            return;
                        }
                        int size = details.length - 1;
                        total += currentValue - details[0];
                        System.arraycopy(details, 1, details, 0, size);
                        details[size] = currentValue;
                        startInterval += detailInterval;
                    }
                }
            }
        }
    }

    public void createDocument(Object document, String signature) {

        counter.increment();

        if (counter.getCount() >= this.requestLimit)
        {
            return;
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(apiUrl);

            httpPost.setHeader("Content-Type", "application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String documentJson = objectMapper.writeValueAsString(document);

            String requestBody = String.format("{ \"product_document\": \"%s\", \"document_format\": \"MANUAL\", \"type\": \"LP_INTRODUCE_GOODS\", \"signature\": \"%s\" }", documentJson, signature);
            StringEntity entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                int statusCode = response.getCode();
                if (statusCode == 200) {
                    System.out.println("Document successfully created!");
                } else {
                    System.out.println("Failed to create the document. Status code: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final String apiUrl = "https://ismp.crpt.ru/api/v3/lk/documents/create";
        CrptApi crptApi = new CrptApi(apiUrl, 5, 1, TimeUnit.SECONDS);

        Description description = new Description("1234567890");

        Product product1 = new Product("cert1", "2023-07-27", "123", "owner1", "producer1", "2023-07-27", "tnved1", "uit1", "uitu1");
        Product product2 = new Product("cert2", "2023-07-28", "456", "owner2", "producer2", "2023-07-28", "tnved2", "uit2", "uitu2");
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);

        Document document = new Document(description, "doc123", "approved", "type1", true, "owner_inn", "participant_inn", "producer_inn", "2023-07-27", "type2", products, "2023-07-27", "reg123");
        String signature = "<Открепленная подпись в base64>";

        for (int i = 0; i < 10; ++i)
        {
            crptApi.createDocument(document, signature);
        }
    }
}
