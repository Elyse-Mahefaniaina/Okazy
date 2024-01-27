package okazy.repository;

import okazy.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
public interface MessageRepository extends MongoRepository<Message, Integer> {

    @Query("{ $or: [ { 'senderId' : ?0, 'receiverId' : ?1 }, { 'senderId' : ?1, 'receiverId' : ?0 } ], $orderby: { 'timestamp' : 1 } }")
    List<Message> findBySenderIdAndReceiverIdOrderByTimestampAsc(String senderId, String receiverId);

}
