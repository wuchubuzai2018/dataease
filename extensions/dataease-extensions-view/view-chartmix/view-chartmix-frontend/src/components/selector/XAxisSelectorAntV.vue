<template>
  <div style="width: 100%">
    <el-col>
      <el-form
        ref="axisForm"
        :model="axisForm"
        label-width="80px"
        size="mini"
      >
        <el-form-item
          :label="$t('chart.show')"
          class="form-item"
        >
          <el-checkbox
            v-model="axisForm.show"
            @change="changeXAxisStyle('show')"
          >{{ $t('chart.show') }}
          </el-checkbox>
        </el-form-item>
        <div v-if="axisForm.show">
          <el-form-item
            :label="$t('chart.position')"
            class="form-item"
          >
            <el-radio-group
              v-model="axisForm.position"
              size="mini"
              @change="changeXAxisStyle('position')"
            >
              <div v-if="chart.type !== 'bidirectional-bar'">
                <el-radio-button label="top">{{ $t('chart.text_pos_top') }}</el-radio-button>
                <el-radio-button label="bottom">{{ $t('chart.text_pos_bottom') }}</el-radio-button>
              </div>
              <div v-else-if="chart.type === 'bidirectional-bar'">
                <el-radio-button label="top">{{ $t('chart.text_pos_left') }}</el-radio-button>
                <el-radio-button label="bottom">{{ $t('chart.text_pos_center') }}</el-radio-button>
              </div>
            </el-radio-group>
          </el-form-item>
          <el-form-item
            :label="$t('chart.name')"
            class="form-item"
          >
            <el-input
              v-model="axisForm.name"
              size="mini"
              @blur="changeXAxisStyle('name')"
            />
          </el-form-item>
          <el-form-item
            :label="$t('chart.axis_name_color')"
            class="form-item"
          >
            <el-color-picker
              v-model="axisForm.nameTextStyle.color"
              class="color-picker-style"
              :predefine="predefineColors"
              @change="changeXAxisStyle('nameTextStyle')"
            />
          </el-form-item>
          <el-form-item
            :label="$t('chart.axis_name_fontsize')"
            class="form-item"
          >
            <el-select
              v-model="axisForm.nameTextStyle.fontSize"
              :placeholder="$t('chart.axis_name_fontsize')"
              @change="changeXAxisStyle('nameTextStyle')"
            >
              <el-option
                v-for="option in fontSize"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-divider/>
          <el-form-item
            :label="$t('chart.axis_show')"
            class="form-item"
          >
            <el-checkbox
              v-model="axisForm.axisLine.show"
              @change="changeXAxisStyle('axisLine')"
            >{{ $t('chart.axis_show') }}
            </el-checkbox>
          </el-form-item>
          <el-form-item
            :label="$t('chart.grid_show')"
            class="form-item"
          >
            <el-checkbox
              v-model="axisForm.splitLine.show"
              @change="changeXAxisStyle('splitLine')"
            >{{ $t('chart.grid_show') }}
            </el-checkbox>
          </el-form-item>
          <span v-if="axisForm.splitLine.show">
            <el-form-item
              :label="$t('chart.grid_color')"
              class="form-item"
            >
              <el-color-picker
                v-model="axisForm.splitLine.lineStyle.color"
                class="el-color-picker"
                :predefine="predefineColors"
                @change="changeXAxisStyle('splitLine')"
              />
            </el-form-item>
            <el-form-item
              :label="$t('chart.grid_width')"
              class="form-item form-item-slider"
            >
              <el-slider
                v-model="axisForm.splitLine.lineStyle.width"
                :min="1"
                :max="10"
                show-input
                :show-input-controls="false"
                input-size="mini"
                @change="changeXAxisStyle('splitLine')"
              />
            </el-form-item>


            <el-form-item
              :label="$t('chart.dash_show')"
              class="form-item"
            >
              <el-checkbox
                v-model="axisForm.splitLine.enableDash"
                @change="changeXAxisStyle('splitLine')"
              >{{ $t('chart.dash_show') }}</el-checkbox>
            </el-form-item>
            <template v-if="axisForm.splitLine.enableDash">
              <el-form-item
                :label="$t('chart.dash_width')"
                class="form-item form-item-slider"
              >
                <el-slider
                  v-model="axisForm.splitLine.dashStyle.width"
                  :min="1"
                  :max="10"
                  show-input
                  :show-input-controls="false"
                  input-size="mini"
                  @change="changeXAxisStyle('splitLine')"
                />
              </el-form-item>
              <el-form-item
                :label="$t('chart.dash_offset')"
                class="form-item form-item-slider"
              >
                <el-slider
                  v-model="axisForm.splitLine.dashStyle.offset"
                  :min="1"
                  :max="10"
                  show-input
                  :show-input-controls="false"
                  input-size="mini"
                  @change="changeXAxisStyle('splitLine')"
                />
              </el-form-item>
            </template>

          </span>
          <el-divider/>
          <el-form-item
            :label="$t('chart.axis_label_show')"
            class="form-item"
          >
            <el-checkbox
              v-model="axisForm.axisLabel.show"
              @change="changeXAxisStyle('axisLabel')"
            >{{ $t('chart.axis_label_show') }}
            </el-checkbox>
          </el-form-item>
          <span v-if="axisForm.axisLabel.show">
            <el-form-item
              :label="$t('chart.axis_label_color')"
              class="form-item"
            >
              <el-color-picker
                v-model="axisForm.axisLabel.color"
                class="el-color-picker"
                :predefine="predefineColors"
                @change="changeXAxisStyle('axisLabel')"
              />
            </el-form-item>
            <el-form-item
              :label="$t('chart.axis_label_rotate')"
              class="form-item form-item-slider"
            >
              <el-slider
                v-model="axisForm.axisLabel.rotate"
                show-input
                :show-input-controls="false"
                :min="-90"
                :max="90"
                input-size="mini"
                @change="changeXAxisStyle('axisLabel')"
              />
            </el-form-item>
            <el-form-item
              :label="$t('chart.axis_label_fontsize')"
              class="form-item"
            >
              <el-select
                v-model="axisForm.axisLabel.fontSize"
                :placeholder="$t('chart.axis_label_fontsize')"
                @change="changeXAxisStyle('axisLabel')"
              >
                <el-option
                  v-for="option in fontSize"
                  :key="option.value"
                  :label="option.name"
                  :value="option.value"
                />
              </el-select>
            </el-form-item>

            <span v-if="chart.type && chart.type.includes('horizontal')">
              <el-form-item
                :label="$t('chart.value_formatter_type')"
                class="form-item"
              >
                <el-select
                  v-model="axisForm.axisLabelFormatter.type"
                  @change="changeXAxisStyle('axisLabelFormatter')"
                >
                  <el-option
                    v-for="type in typeList"
                    :key="type.value"
                    :label="$t('chart.' + type.name)"
                    :value="type.value"
                  />
                </el-select>
              </el-form-item>

              <el-form-item
                v-if="axisForm.axisLabelFormatter.type !== 'auto'"
                :label="$t('chart.value_formatter_decimal_count')"
                class="form-item"
              >
                <el-input-number
                  v-model="axisForm.axisLabelFormatter.decimalCount"
                  :precision="0"
                  :min="0"
                  :max="10"
                  size="mini"
                  @change="changeXAxisStyle('axisLabelFormatter')"
                />
              </el-form-item>

              <el-form-item
                v-if="axisForm.axisLabelFormatter.type !== 'percent'"
                :label="$t('chart.value_formatter_unit')"
                class="form-item"
              >
                <el-select
                  v-model="axisForm.axisLabelFormatter.unit"
                  :placeholder="$t('chart.pls_select_field')"
                  size="mini"
                  @change="changeXAxisStyle('axisLabelFormatter')"
                >
                  <el-option
                    v-for="item in unitList"
                    :key="item.value"
                    :label="$t('chart.' + item.name)"
                    :value="item.value"
                  />
                </el-select>
              </el-form-item>

              <el-form-item
                :label="$t('chart.value_formatter_suffix')"
                class="form-item"
              >
                <el-input
                  v-model="axisForm.axisLabelFormatter.suffix"
                  size="mini"
                  clearable
                  :placeholder="$t('commons.input_content')"
                  @change="changeXAxisStyle('axisLabelFormatter')"
                />
              </el-form-item>

              <el-form-item
                :label="$t('chart.value_formatter_thousand_separator')"
                class="form-item"
              >
                <el-checkbox
                  v-model="axisForm.axisLabelFormatter.thousandSeparator"
                  @change="changeXAxisStyle('axisLabelFormatter')"
                />
              </el-form-item>
            </span>
          </span>
        </div>
      </el-form>
    </el-col>
  </div>
</template>

<script>
import {COLOR_PANEL, DEFAULT_XAXIS_STYLE} from '../../utils/map'
import {formatterType, unitList} from '../../utils/formatter'

export default {
  name: 'XAxisSelectorAntV',
  props: {
    param: {
      type: Object,
      required: true
    },
    chart: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      axisForm: JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE)),
      isSetting: false,
      fontSize: [],
      predefineColors: COLOR_PANEL,
      typeList: formatterType,
      unitList: unitList
    }
  },
  watch: {
    'chart': {
      handler: function () {
        this.initData()
      }
    }
  },
  mounted() {
    this.init()
    this.initData()
  },
  methods: {
    initData() {
      const chart = JSON.parse(JSON.stringify(this.chart))
      if (chart.customStyle) {
        let customStyle = null
        if (Object.prototype.toString.call(chart.customStyle) === '[object Object]') {
          customStyle = JSON.parse(JSON.stringify(chart.customStyle))
        } else {
          customStyle = JSON.parse(chart.customStyle)
        }
        if (customStyle.xAxis) {
          this.axisForm = customStyle.xAxis
          if (!this.axisForm.splitLine) {
            this.axisForm.splitLine = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.splitLine))
          }
          if (!this.axisForm.nameTextStyle) {
            this.axisForm.nameTextStyle = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.nameTextStyle))
          }
          if (!this.axisForm.axisValue) {
            this.axisForm.axisValue = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.axisValue))
          }
          if (!this.axisForm.axisLabelFormatter) {
            this.axisForm.axisLabelFormatter = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.axisLabelFormatter))
          }
          if (!this.axisForm.axisLine) {
            this.axisForm.axisLine = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.axisLine))
          }
          if (this.axisForm.splitLine.enableDash === undefined) {
            this.axisForm.splitLine.enableDash = DEFAULT_XAXIS_STYLE.splitLine.enableDash
          }
          if (!this.axisForm.splitLine.dashStyle) {
            this.axisForm.splitLine.dashStyle = JSON.parse(JSON.stringify(DEFAULT_XAXIS_STYLE.splitLine.dashStyle))
          }
        }
      }
    },
    init() {
      const arr = []
      for (let i = 6; i <= 40; i = i + 2) {
        arr.push({
          name: i + '',
          value: i + ''
        })
      }
      this.fontSize = arr
    },
    changeXAxisStyle(modifyName) {
      if (!this.axisForm.show) {
        this.isSetting = false
      }
      if (this.axisForm.axisValue.splitCount && (parseInt(this.axisForm.axisValue.splitCount) > 100 || parseInt(this.axisForm.axisValue.splitCount) < 0)) {
        this.$message({
          message: this.$t('chart.splitCount_less_100'),
          type: 'error'
        })
        return
      }
      this.axisForm['modifyName'] = modifyName
      this.$emit('onChangeXAxisForm', this.axisForm)
    },

  }
}
</script>

<style scoped>
.el-divider--horizontal {
  margin: 10px 0
}

.shape-item {
  padding: 6px;
  border: none;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.form-item-slider ::v-deep .el-form-item__label {
  font-size: 12px;
  line-height: 38px;
}

.form-item ::v-deep .el-form-item__label {
  font-size: 12px;
}

.form-item ::v-deep .el-checkbox__label {
  font-size: 12px;
}

.form-item ::v-deep .el-radio__label {
  font-size: 12px;
}

.el-select-dropdown__item {
  padding: 0 20px;
}

span {
  font-size: 12px
}

.el-form-item {
  margin-bottom: 6px;
}

.switch-style {
  position: absolute;
  right: 10px;
  margin-top: -4px;
}

.color-picker-style {
  cursor: pointer;
  z-index: 1003;
}
</style>
