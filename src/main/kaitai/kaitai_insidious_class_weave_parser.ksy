meta:
  id: kaitai_insidious_class_weave_parser
seq:
  - id: class_count
    type: u4be
  - id: class_info
    type: class_info
    repeat: expr
    repeat-expr: class_count
types:
  class_info:
    seq:
      - id: class_id
        type: u4be
      - id: container
        type: str_with_len
      - id: file_name
        type: str_with_len
      - id: class_name
        type: str_with_len
      - id: log_level
        type: str_with_len
      - id: hash
        type: str_with_len
      - id: class_loader_identifier
        type: str_with_len
      - id: interface_count
        type: u4be
      - id: interface_names
        type: str_with_len
        repeat: expr
        repeat-expr: interface_count
      - id: signature
        type: str_with_len
      - id: superclass
        type: str_with_len
      - id: probe_count
        type: u4be
      - id: probe_list
        type: probe_info
        repeat: expr
        repeat-expr: probe_count
      - id: method_count
        type: u4be
      - id: method_list
        type: method_info
        repeat: expr
        repeat-expr: method_count
  method_info:
    seq:
      - id: class_id
        type: u4be
      - id: method_id
        type: u4be
      - id: method_name
        type: str_with_len
      - id: method_descriptor
        type: str_with_len
      - id: access
        type: u4be
      - id: source_file_name
        type: str_with_len
      - id: method_hash
        type: str_with_len
  probe_info:
    seq:
      - id: class_id
        type: u4be
      - id: method_id
        type: u4be
      - id: data_id
        type: u4be
      - id: line_number
        type: u4be
      - id: instruction_index
        type: u4be
      - id: event_type
        type: u4be
        enum: event_type
      - id: value_descriptor
        type: u4be
        enum: descriptor
      - id: attributes
        type: str_with_len
  str_with_len:
    seq:
      - id: len
        type: u4be
      - id: value
        size: len
        type: str
        encoding: UTF-8
enums:
  descriptor:
    0: boolean
    1: byte
    2: char
    3: short
    4: integer
    5: long
    6: float
    7: double
    8: integerobject
    9: characterobject
    10: booleanobject
    11: floatobject
    12: doubleobject
    13: shortobject
    14: object
    15: void
  event_type:
    0: reserved
    1: method_entry
    2: method_param
    3: method_object_initialized
    4: method_normal_exit
    5: method_throw
    6: method_exceptional_exit
    7: call
    8: call_param
    9: call_return
    10: catch_label
    11: catch
    12: new_object
    13: new_object_created
    14: get_instance_field
    15: get_instance_field_result
    16: get_static_field
    17: put_instance_field
    18: put_instance_field_value
    19: put_instance_field_before_initialization
    20: put_static_field
    21: array_load
    22: array_load_index
    23: array_load_result
    24: array_store
    25: array_store_index
    26: array_store_value
    27: new_array
    28: new_array_result
    29: multi_new_array
    30: multi_new_array_owner
    31: multi_new_array_element
    32: array_length
    33: array_length_result
    34: monitor_enter
    35: monitor_enter_result
    36: monitor_exit
    37: object_constant_load
    38: object_instanceof
    39: object_instanceof_result
    40: invoke_dynamic
    41: invoke_dynamic_param
    42: invoke_dynamic_result
    43: label
    44: jump
    45: local_load
    46: local_store
    47: local_increment
    48: ret
    49: divide
    50: line_number