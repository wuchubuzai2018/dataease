<template>
  <div style="width: 100%;">
    <el-col>
      <el-form
        ref="labelForm"
        :model="labelForm"
        label-width="80px"
        size="mini"
      >
        <el-form-item
          v-show="showProperty('show')"
          :label="$t('chart.show')"
          class="form-item"
        >
          <el-checkbox
            v-model="labelForm.show"
            @change="changeLabelAttr('show')"
          ></el-checkbox>
        </el-form-item>
        <div v-show="labelForm.show">
          <el-form-item
            v-show="showProperty('labelContent')"
            :label="$t('chart.label_content')"
            class="form-item"
          >
            <el-checkbox-group
              v-model="labelForm.labelContent"
              :label="$t('chart.label_content')"
              :min="1"
              :max="3"
              @change="changeLabelAttr('labelContent')"
            >
              <el-checkbox
                v-for="option in labelContentOptions"
                :key="option.value"
                :label="option.value"
              >{{ option.name }}</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item
            v-show="showProperty('fontSize')"
            :label="$t('chart.text_fontsize')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.fontSize"
              :placeholder="$t('chart.text_fontsize')"
              size="mini"
              @change="changeLabelAttr('fontSize')"
            >
              <el-option
                v-for="option in fontSize"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            v-show="showProperty('color')"
            :label="$t('chart.text_color')"
            class="form-item"
          >
            <el-color-picker
              v-model="labelForm.color"
              class="color-picker-style"
              :predefine="predefineColors"
              @change="changeLabelAttr('color')"
            />
          </el-form-item>
          <el-form-item
            v-show="showProperty('position-v')"
            :label="$t('chart.label_position')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.position"
              :placeholder="$t('chart.label_position')"
              @change="changeLabelAttr('position')"
            >
              <el-option
                v-for="option in labelPositionV"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            v-show="showProperty('position-pie')"
            :label="$t('chart.label_position')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.position"
              :placeholder="$t('chart.label_position')"
              @change="changeLabelAttr('position')"
            >
              <el-option
                v-for="option in labelPositionPie"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            v-show="showProperty('position-h')"
            :label="$t('chart.label_position')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.position"
              :placeholder="$t('chart.label_position')"
              @change="changeLabelAttr('position')"
            >
              <el-option
                v-for="option in labelPositionH"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            v-show="showProperty('reserveDecimalCount') && (chart.type.includes('percentage') || labelForm.labelContent.includes('proportion'))"
            :label="$t('chart.label_reserve_decimal_count')"
            class="form-item"
          >
            <el-radio-group
              v-model="labelForm.reserveDecimalCount"
              :label="$t('chart.label_reserve_decimal_count')"
              @change="changeLabelAttr('reserveDecimalCount')"
            >
              <el-radio
                v-for="option in reserveDecimalCountOptions"
                :key="option.value"
                :label="option.value"
              >{{ option.name }}</el-radio>
            </el-radio-group>
          </el-form-item>
        </div>
        <el-form-item
          v-show="showProperty('conversion')"
          :label="$t('chart.show_conversion')"
          class="form-item"
        >
          <el-checkbox
            v-model="labelForm.showConversion"
            @change="changeLabelAttr('showConversion')"
          ></el-checkbox>
        </el-form-item>
        <el-form-item
          v-show="labelForm.showConversion && showProperty('conversionLabel')"
          :label="$t('chart.conversion_label')"
          class="form-item"
        >
          <el-input
            v-model="labelForm.conversionLabel"
            :maxlength="20"
            @change="changeLabelAttr('conversionLabel')"
          ></el-input>
        </el-form-item>
      </el-form>

      <el-form
        v-show="showProperty('labelGauge') && chart.type && (chart.type.includes('gauge') || chart.type.includes('liquid'))"
        ref="labelForm"
        :model="labelForm"
        label-width="80px"
        size="mini"
      >
        <el-form-item
          :label="$t('chart.show')"
          class="form-item"
        >
          <el-checkbox
            v-model="labelForm.show"
            @change="changeLabelAttr('show')"
          >{{ $t('chart.show') }}</el-checkbox>
        </el-form-item>
        <div v-show="labelForm.show">
          <el-form-item
            :label="$t('chart.text_fontsize')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.fontSize"
              :placeholder="$t('chart.text_fontsize')"
              size="mini"
              @change="changeLabelAttr('fontSize')"
            >
              <el-option
                v-for="option in fontSize"
                :key="option.value"
                :label="option.name"
                :value="option.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item
            :label="$t('chart.text_color')"
            class="form-item"
          >
            <el-color-picker
              v-model="labelForm.color"
              class="color-picker-style"
              :predefine="predefineColors"
              @change="changeLabelAttr('color')"
            />
          </el-form-item>
          <el-form-item
            :label="$t('chart.value_formatter_type')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.gaugeLabelFormatter.type"
              @change="changeLabelAttr('gaugeLabelFormatter')"
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
            v-show="labelForm.gaugeLabelFormatter.type !== 'auto'"
            :label="$t('chart.value_formatter_decimal_count')"
            class="form-item"
          >
            <el-input-number
              v-model="labelForm.gaugeLabelFormatter.decimalCount"
              :precision="0"
              :min="0"
              :max="10"
              size="mini"
              @change="changeLabelAttr('gaugeLabelFormatter')"
            />
          </el-form-item>
          <el-form-item
            v-show="labelForm.gaugeLabelFormatter.type !== 'percent'"
            :label="$t('chart.value_formatter_unit')"
            class="form-item"
          >
            <el-select
              v-model="labelForm.gaugeLabelFormatter.unit"
              :placeholder="$t('chart.pls_select_field')"
              size="mini"
              @change="changeLabelAttr('gaugeLabelFormatter')"
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
              v-model="labelForm.gaugeLabelFormatter.suffix"
              size="mini"
              clearable
              :placeholder="$t('commons.input_content')"
              @change="changeLabelAttr('gaugeLabelFormatter')"
            />
          </el-form-item>
          <el-form-item
            :label="$t('chart.value_formatter_thousand_separator')"
            class="form-item"
          >
            <el-checkbox
              v-model="labelForm.gaugeLabelFormatter.thousandSeparator"
              @change="changeLabelAttr('gaugeLabelFormatter')"
            />
          </el-form-item>
        </div>
      </el-form>
    </el-col>
  </div>
</template>

<script>
import { COLOR_PANEL, DEFAULT_LABEL } from '../../chart/chart'
import { formatterType, unitList } from '@/views/chart/chart/formatter'

export default {
  name: 'LabelSelectorAntV',
  props: {
    param: {
      type: Object,
      required: true
    },
    chart: {
      type: Object,
      required: true
    },
    propertyInner: {
      type: Array,
      required: false,
      default: function() {
        return []
      }
    }
  },
  data: function() {
    return {
      labelForm: JSON.parse(JSON.stringify(DEFAULT_LABEL)),
      fontSize: [],
      isSetting: false,
      labelPosition: [],
      labelPositionPie: [
        { name: this.$t('chart.inside'), value: 'inner' },
        { name: this.$t('chart.outside'), value: 'outer' }
      ],
      labelPositionH: [
        { name: this.$t('chart.text_pos_left'), value: 'left' },
        { name: this.$t('chart.center'), value: 'middle' },
        { name: this.$t('chart.text_pos_right'), value: 'right' }
      ],
      labelPositionV: [
        { name: this.$t('chart.text_pos_top'), value: 'top' },
        { name: this.$t('chart.center'), value: 'middle' },
        { name: this.$t('chart.text_pos_bottom'), value: 'bottom' }
      ],
      predefineColors: COLOR_PANEL,
      typeList: formatterType,
      unitList: unitList,
      reserveDecimalCountOptions: [
        { name: this.$t('chart.reserve_zero'), value: 0 },
        { name: this.$t('chart.reserve_one'), value: 1 },
        { name: this.$t('chart.reserve_two'), value: 2 }
      ]
    }
  },
  computed: {
    labelContentOptions() {
      if (this.chart.type.includes('pie')) {
        return [
          { name: this.$t('chart.dimension'), value: 'dimension' },
          { name: this.$t('chart.quota'), value: 'quota' },
          { name: this.$t('chart.proportion'), value: 'proportion' }
        ]
      }
      if (this.chart.type.includes('bar')) {
        return [
          { name: this.$t('chart.chart_group'), value: 'group' },
          { name: this.$t('chart.stack_item'), value: 'stack' },
          { name: this.$t('chart.quota'), value: 'quota' }
        ]
      }
      return []
    }
  },
  watch: {
    'chart': {
      handler: function() {
        this.initOptions()
        this.initData()
      }
    }
  },
  mounted() {
    if (this.showProperty('position-pie')) {
      this.labelForm.position = 'outer'
    }
    this.init()
    this.initOptions()
    this.initData()
  },
  methods: {
    initData() {
      const chart = JSON.parse(JSON.stringify(this.chart))
      if (chart.customAttr) {
        let customAttr = null
        if (Object.prototype.toString.call(chart.customAttr) === '[object Object]') {
          customAttr = JSON.parse(JSON.stringify(chart.customAttr))
        } else {
          customAttr = JSON.parse(chart.customAttr)
        }
        if (customAttr.label) {
          this.labelForm = customAttr.label
          if (!this.labelForm.labelLine) {
            this.labelForm.labelLine = JSON.parse(JSON.stringify(DEFAULT_LABEL.labelLine))
          }
          if (!this.labelForm.gaugeLabelFormatter) {
            this.labelForm.gaugeLabelFormatter = JSON.parse(JSON.stringify(DEFAULT_LABEL.gaugeLabelFormatter))
          }
          if ((this.labelForm.reserveDecimalCount ?? '') === '') {
            this.labelForm.reserveDecimalCount = 2
          }
          if (!this.labelForm.labelContent) {
            this.labelForm.labelContent = ['quota']
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
    changeLabelAttr(modifyName) {
      if (!this.labelForm.show) {
        this.isSetting = false
      }
      this.labelForm['modifyName'] = modifyName
      this.$emit('onLabelChange', this.labelForm)
    },
    initOptions() {
      const type = this.chart.type
      if (type) {
        if (type.includes('horizontal') || type === 'funnel') {
          this.labelPosition = this.labelPositionH
        } else if (type.includes('pie')) {
          this.labelPosition = this.labelPositionPie
        } else if (type === 'percentage-bar-stack') {
          // 百分比堆叠柱状图的标签位置为 top 的话最顶上的标签会看不到，这个是 G2plot 的 bug，所以这边暂时先把默认值设置为 middle
          this.labelForm.position = 'middle'
        } else {
          this.labelPosition = this.labelPositionV
        }
      }
    },
    /*
      判断该属性是否应该出现在当前视图的标签属性编辑列表中
     */
    showProperty(property) {
      return this.propertyInner.includes(property)
    }
  }
}
</script>

<style scoped>
.shape-item{
  padding: 6px;
  border: none;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.form-item-slider ::v-deep .el-form-item__label{
  font-size: 12px;
  line-height: 38px;
}
.form-item ::v-deep .el-form-item__label{
  font-size: 12px;
}

.form-item ::v-deep .el-checkbox__label {
  font-size: 12px;
}
.form-item ::v-deep .el-radio__label {
  font-size: 12px;
}

.el-select-dropdown__item{
  padding: 0 20px;
}
  span{
    font-size: 12px
  }
  .el-form-item{
    margin-bottom: 6px;
  }

  .switch-style{
    position: absolute;
    right: 10px;
    margin-top: -4px;
  }
  .color-picker-style{
    cursor: pointer;
    z-index: 1003;
  }
</style>
