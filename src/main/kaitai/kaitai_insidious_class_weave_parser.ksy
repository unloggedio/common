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
      - id: class_name
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
      - id: has_source_file_name
        type: b1
      - id: source_file_name
        type: str_with_len
        if: has_source_file_name
      - id: has_method_hash
        type: b1
      - id: method_hash
        type: str_with_len
        if: has_method_hash
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
        type: str_with_len
      - id: value_descriptor
        type: str_with_len
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