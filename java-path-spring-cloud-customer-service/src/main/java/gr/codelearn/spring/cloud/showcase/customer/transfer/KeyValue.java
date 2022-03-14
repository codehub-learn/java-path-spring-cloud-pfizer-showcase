package gr.codelearn.spring.cloud.showcase.customer.transfer;

import lombok.Value;

@Value
public class KeyValue<K, V> {
	K key;
	V value;
}
